package bearmaps.proj2d;

import bearmaps.proj2ab.Point;
import bearmaps.proj2c.streetmap.Node;
import bearmaps.proj2c.streetmap.StreetMapGraph;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

    private Map<Point, Node> pointToNode; // set a map to store the point and the corresponding node
    private KDTree kdTree; // set a KDTree to store the points
    private Trie trie; // set a trie to store the names of the nodes

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        List<Node> nodes = this.getNodes();
        pointToNode = new HashMap<>();
        kdTree = new KDTree();
        trie = new Trie();

        /* insert nodes that have neighbors into the KDTree */
        for (Node node : nodes) {
            if (node.name() != null && !neighbors(node.id()).isEmpty()) {
                Point point = convertNodeToPoint(node);
                pointToNode.put(point, node);
                kdTree.insert(point);
            }
        }
    }

    /**
     * convert a node to a point
     * @param node the node to be converted
     * @return the point converted from the node
     */
    private Point convertNodeToPoint(Node node) {
        return new Point(node.lon(), node.lat());
    }

    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        return pointToNode.get(kdTree.nearest(lon, lat)).id();
    }

    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        prefix = cleanString(prefix); // clean the prefix
        return new LinkedList<>();
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        return new LinkedList<>();
    }

    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }
}
