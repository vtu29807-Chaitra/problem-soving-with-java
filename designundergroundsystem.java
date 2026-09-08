import java.util.*;

class UndergroundSystem {

    // Stores check-in information for each passenger
    private Map<Integer, CheckIn> checkIns;

    // Stores total travel time and number of trips for each route
    private Map<String, Route> routes;

    public UndergroundSystem() {
        checkIns = new HashMap<Integer, CheckIn>();
        routes = new HashMap<String, Route>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn checkIn = checkIns.get(id);

        String routeName = checkIn.stationName + "#" + stationName;

        int travelTime = t - checkIn.time;

        if (!routes.containsKey(routeName)) {
            routes.put(routeName, new Route());
        }

        Route route = routes.get(routeName);
        route.totalTime += travelTime;
        route.count++;

        checkIns.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String routeName = startStation + "#" + endStation;

        Route route = routes.get(routeName);

        return (double) route.totalTime / route.count;
    }

    // Class to store check-in information
    class CheckIn {
        String stationName;
        int time;

        CheckIn(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    // Class to store route information
    class Route {
        int totalTime;
        int count;

        Route() {
            totalTime = 0;
            count = 0;
        }
    }
}
