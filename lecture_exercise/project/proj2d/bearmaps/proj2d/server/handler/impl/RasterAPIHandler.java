package bearmaps.proj2d.server.handler.impl;

import bearmaps.proj2d.AugmentedStreetMapGraph;
import bearmaps.proj2d.server.handler.APIRouteHandler;
import spark.Request;
import spark.Response;
import bearmaps.proj2d.utils.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bearmaps.proj2d.utils.Constants.SEMANTIC_STREET_GRAPH;
import static bearmaps.proj2d.utils.Constants.ROUTE_LIST;

/**
 * Handles requests from the web browser for map images. These images
 * will be rastered into one large image to be displayed to the user.
 * @author rahul, Josh Hug, _________
 */
public class RasterAPIHandler extends APIRouteHandler<Map<String, Double>, Map<String, Object>> {

    /**
     * Each raster request to the server will have the following parameters
     * as keys in the params map accessible by,
     * i.e., params.get("ullat") inside RasterAPIHandler.processRequest(). <br>
     * ullat : upper left corner latitude, <br> ullon : upper left corner longitude, <br>
     * lrlat : lower right corner latitude,<br> lrlon : lower right corner longitude <br>
     * w : user viewport window width in pixels,<br> h : user viewport height in pixels.
     **/
    private static final String[] REQUIRED_RASTER_REQUEST_PARAMS = {"ullat", "ullon", "lrlat",
            "lrlon", "w", "h"};

    /**
     * The result of rastering must be a map containing all of the
     * fields listed in the comments for RasterAPIHandler.processRequest.
     **/
    private static final String[] REQUIRED_RASTER_RESULT_PARAMS = {"render_grid", "raster_ul_lon",
            "raster_ul_lat", "raster_lr_lon", "raster_lr_lat", "depth", "query_success"};


    @Override
    protected Map<String, Double> parseRequestParams(Request request) {
        return getRequestParams(request, REQUIRED_RASTER_REQUEST_PARAMS);
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param requestParams Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @param response : Not used by this function. You may ignore.
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image;
     *                    can also be interpreted as the length of the numbers in the image
     *                    string. <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    @Override
    public Map<String, Object> processRequest(Map<String, Double> requestParams, Response response) {
        /**
         * Corner Case 1:
         * if the user goes to the edge of the map beyond where data is available
         * or the query box is so zoomed out that it includes the entire dataset
         * simply return what data you do have available.
         */
        if (zoomedOut(requestParams)) {
            return fullQuery();
        }

        /**
         * Corner Case 2:
         * If the query box is completely outside of the map
         * or receives a query box that doesn't make any sense
         * return a query fail
         */
        if (notValueQuery(requestParams)) {
            return queryFail();
        }

        // normal case
        return getValueResult(requestParams);
    }

    /**
     * find the start and end of the query box
     * @param requestParams 
     * @param depth the depth of the query box
     * @return xStart, yStart, xEnd, yEnd in a array
     */
    private int[] XYBoard(final Map<String, Double> requestParams, final int depth) {
        int xStart = Math.max((int) Math.floor((requestParams.get("ullon") - Constants.ROOT_ULLON) / (Constants.ROOT_LRLON - Constants.ROOT_ULLON) * Math.pow(2, depth)), 0);
        int yStart = Math.max((int) Math.floor((Constants.ROOT_ULLAT - requestParams.get("ullat")) / (Constants.ROOT_ULLAT - Constants.ROOT_LRLAT) * Math.pow(2, depth)), 0);
        int xEnd = Math.min((int) Math.floor((requestParams.get("lrlon") - Constants.ROOT_ULLON) / (Constants.ROOT_LRLON - Constants.ROOT_ULLON) * Math.pow(2, depth)), (int) Math.pow(2, depth) - 1);
        int yEnd = Math.min((int) Math.floor((Constants.ROOT_ULLAT - requestParams.get("lrlat")) / (Constants.ROOT_ULLAT - Constants.ROOT_LRLAT) * Math.pow(2, depth)), (int) Math.pow(2, depth) - 1);

        return new int[]{xStart, yStart, xEnd, yEnd};
    }

    /**
     * transfer the XYBoard to rasterBoard
     * @param XYBoard the whole xy board
     * @param depth the depth of the query box
     * @return rasterBoard in a array
     */
    private double[] rasterBoard(final int[] XYBoard, final int depth) {
        double raster_ul_lon = Constants.ROOT_ULLON + XYBoard[0] * (Constants.ROOT_LRLON - Constants.ROOT_ULLON) / Math.pow(2, depth);
        double raster_ul_lat = Constants.ROOT_ULLAT - XYBoard[1] * (Constants.ROOT_ULLAT - Constants.ROOT_LRLAT) / Math.pow(2, depth);
        double raster_lr_lon = Constants.ROOT_ULLON + (XYBoard[2] + 1) * (Constants.ROOT_LRLON - Constants.ROOT_ULLON) / Math.pow(2, depth);
        double raster_lr_lat = Constants.ROOT_ULLAT - (XYBoard[3] + 1) * (Constants.ROOT_ULLAT - Constants.ROOT_LRLAT) / Math.pow(2, depth);

        return new double[]{raster_ul_lon, raster_ul_lat, raster_lr_lon, raster_lr_lat};
    }

    /**
     * get the value result
     * @param requestParams the request params
     * @return result
     */
    private Map<String, Object> getValueResult(final Map<String, Double> requestParams) {
        Map<String, Object> results = new HashMap<>();
        final int depth = findDepth(requestParams);
        final int[] XYBoard = XYBoard(requestParams, depth);
        final double[] rasterBoard = rasterBoard(XYBoard, depth);

        results.put("depth", depth);
        results.put("query_success", true);
        results.put("render_grid", findTiles(depth, XYBoard));
        results.put("raster_ul_lon", rasterBoard[0]);
        results.put("raster_ul_lat", rasterBoard[1]);
        results.put("raster_lr_lon", rasterBoard[2]);
        results.put("raster_lr_lat", rasterBoard[3]);
        return results;
    }

    /**
     * find the tiles of the query box
     * with xStart, yStart, xEnd, yEnd
     * and depth
     * add the images to the render_grid
     * @param depth the depth of the query box
     * @param XYBoard the whole xy board
     * return tiles in a 2D array
     */
    private String[][] findTiles(final int depth, final int[] XYBoard) {
        int xStart = XYBoard[0];
        int yStart = XYBoard[1];
        int xEnd = XYBoard[2];
        int yEnd = XYBoard[3];

        String[][] tiles = new String[yEnd - yStart + 1][xEnd - xStart + 1];
        for (int i = 0; i < yEnd - yStart + 1; i++) {
            for (int j = 0; j < xEnd - xStart + 1; j++) {
                tiles[i][j] = "d" + depth + "_x" + (j + xStart) + "_y" + (i + yStart) + ".png";
            }
        }
        return tiles;
    }

    /**
     * find the depth of the query box
     * get the lonDPP of the query box
     * compare the lonDPP of the query box with the lonDPP of the root
     * if the lonDPP of the query box is smaller than the lonDPP of the root
     * then increase the depth
     * @param requestParams
     * @return depth
     */
    private Integer findDepth(final Map<String, Double> requestParams) {
        double lonDPP = (requestParams.get("lrlon") - requestParams.get("ullon")) / requestParams.get("w");
        int depth = 0;
        double rootLonDPP = (Constants.ROOT_LRLON - Constants.ROOT_ULLON) / Constants.TILE_SIZE;
        while (depth < 7 && rootLonDPP > lonDPP) {
            rootLonDPP /= 2;
            depth++;
        }
        return depth;
    }

    /**
     * find out whether the request is valid
     * include the query box that doesn't make any sense
     * and the query box is completely outside of the map
     * @param requestParams
     * @return boolean
     */
    private boolean notValueQuery(final Map<String, Double> requestParams) {
        return requestParams == null
                || requestParams.get("ullat") <= requestParams.get("lrlat")
                || requestParams.get("ullon") >= requestParams.get("lrlon")
                || requestParams.get("lrlon") <= Constants.ROOT_ULLON
                || requestParams.get("ullon") >= Constants.ROOT_LRLON
                || requestParams.get("lrlat") >= Constants.ROOT_ULLAT
                || requestParams.get("ullat") <= Constants.ROOT_LRLAT;
    }

    /**
     * find out whether the query box is so zoomed out that it includes the entire dataset
     * @param requestParams
     * @return boolean
     */
    private boolean zoomedOut(final Map<String, Double> requestParams) {
        return requestParams.get("lrlon") - requestParams.get("ullon") >= Constants.ROOT_LRLON - Constants.ROOT_ULLON
                && requestParams.get("ullon") <= Constants.ROOT_ULLON
                && requestParams.get("lrlon") >= Constants.ROOT_LRLON
                && requestParams.get("ullat") >= Constants.ROOT_ULLAT
                && requestParams.get("lrlat") <= Constants.ROOT_LRLAT;
    }

    /**
     * full query
     * @return results
     */
    private Map<String, Object> fullQuery() {
        Map<String, Object> results = new HashMap<>();
        results.put("render_grid", new String[][]{{"d0_x0_y0.png"}});
        results.put("raster_ul_lon", Constants.ROOT_ULLON);
        results.put("raster_ul_lat", Constants.ROOT_ULLAT);
        results.put("raster_lr_lon", Constants.ROOT_LRLON);
        results.put("raster_lr_lat", Constants.ROOT_LRLAT);
        results.put("depth", 0);
        results.put("query_success", true);
        return results;
    }

    @Override
    protected Object buildJsonResponse(Map<String, Object> result) {
        boolean rasterSuccess = validateRasteredImgParams(result);

        if (rasterSuccess) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            writeImagesToOutputStream(result, os);
            String encodedImage = Base64.getEncoder().encodeToString(os.toByteArray());
            result.put("b64_encoded_image_data", encodedImage);
        }
        return super.buildJsonResponse(result);
    }

    private Map<String, Object> queryFail() {
        Map<String, Object> results = new HashMap<>();
        results.put("render_grid", null);
        results.put("raster_ul_lon", 0);
        results.put("raster_ul_lat", 0);
        results.put("raster_lr_lon", 0);
        results.put("raster_lr_lat", 0);
        results.put("depth", 0);
        results.put("query_success", false);
        return results;
    }

    /**
     * Validates that Rasterer has returned a result that can be rendered.
     * @param rip : Parameters provided by the rasterer
     */
    private boolean validateRasteredImgParams(Map<String, Object> rip) {
        for (String p : REQUIRED_RASTER_RESULT_PARAMS) {
            if (!rip.containsKey(p)) {
                System.out.println("Your rastering result is missing the " + p + " field.");
                return false;
            }
        }
        if (rip.containsKey("query_success")) {
            boolean success = (boolean) rip.get("query_success");
            if (!success) {
                System.out.println("query_success was reported as a failure");
                return false;
            }
        }
        return true;
    }

    /**
     * Writes the images corresponding to rasteredImgParams to the output stream.
     * In Spring 2016, students had to do this on their own, but in 2017,
     * we made this into provided code since it was just a bit too low level.
     */
    private  void writeImagesToOutputStream(Map<String, Object> rasteredImageParams,
                                                  ByteArrayOutputStream os) {
        String[][] renderGrid = (String[][]) rasteredImageParams.get("render_grid");
        int numVertTiles = renderGrid.length;
        int numHorizTiles = renderGrid[0].length;

        BufferedImage img = new BufferedImage(numHorizTiles * Constants.TILE_SIZE,
                numVertTiles * Constants.TILE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = img.getGraphics();
        int x = 0, y = 0;

        for (int r = 0; r < numVertTiles; r += 1) {
            for (int c = 0; c < numHorizTiles; c += 1) {
                graphic.drawImage(getImage(Constants.IMG_ROOT + renderGrid[r][c]), x, y, null);
                x += Constants.TILE_SIZE;
                if (x >= img.getWidth()) {
                    x = 0;
                    y += Constants.TILE_SIZE;
                }
            }
        }

        /* If there is a route, draw it. */
        double ullon = (double) rasteredImageParams.get("raster_ul_lon"); //tiles.get(0).ulp;
        double ullat = (double) rasteredImageParams.get("raster_ul_lat"); //tiles.get(0).ulp;
        double lrlon = (double) rasteredImageParams.get("raster_lr_lon"); //tiles.get(0).ulp;
        double lrlat = (double) rasteredImageParams.get("raster_lr_lat"); //tiles.get(0).ulp;

        final double wdpp = (lrlon - ullon) / img.getWidth();
        final double hdpp = (ullat - lrlat) / img.getHeight();
        AugmentedStreetMapGraph graph = SEMANTIC_STREET_GRAPH;
        List<Long> route = ROUTE_LIST;

        if (route != null && !route.isEmpty()) {
            Graphics2D g2d = (Graphics2D) graphic;
            g2d.setColor(Constants.ROUTE_STROKE_COLOR);
            g2d.setStroke(new BasicStroke(Constants.ROUTE_STROKE_WIDTH_PX,
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            route.stream().reduce((v, w) -> {
                g2d.drawLine((int) ((graph.lon(v) - ullon) * (1 / wdpp)),
                        (int) ((ullat - graph.lat(v)) * (1 / hdpp)),
                        (int) ((graph.lon(w) - ullon) * (1 / wdpp)),
                        (int) ((ullat - graph.lat(w)) * (1 / hdpp)));
                return w;
            });
        }

        rasteredImageParams.put("raster_width", img.getWidth());
        rasteredImageParams.put("raster_height", img.getHeight());

        try {
            ImageIO.write(img, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BufferedImage getImage(String imgPath) {
        BufferedImage tileImg = null;
        if (tileImg == null) {
            try {
                File in = new File(imgPath);
                tileImg = ImageIO.read(in);
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return tileImg;
    }
}
