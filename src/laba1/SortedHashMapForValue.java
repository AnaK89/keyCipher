package laba1;

import java.util.*;

class SortedHashMapForValue {
  private Map<String, Double> unsortedHashMap;
  private Map<String, Double> sortedHashMap = new LinkedHashMap<>();

  SortedHashMapForValue(Map<String, Double> unsortedHashMap) {
    this.unsortedHashMap = unsortedHashMap;
  }

  Map<String, Double> sorting() {

    List<Double> mapValues = new ArrayList<>(unsortedHashMap.values());
    Collections.sort(mapValues);
    Collections.reverse(mapValues);

    for (Double value : mapValues) {
      for (HashMap.Entry<String, Double> value1 : unsortedHashMap.entrySet()) {
        if (value.equals(value1.getValue())) {
          sortedHashMap.put(value1.getKey(), value);
          unsortedHashMap.remove(value1.getKey());
          break;
        }
      }
    }

    return sortedHashMap;
  }
}
