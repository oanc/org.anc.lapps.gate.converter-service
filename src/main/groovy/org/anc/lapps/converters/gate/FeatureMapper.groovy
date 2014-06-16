package org.anc.lapps.converters.gate

/**
 * @author Keith Suderman
 */
class FeatureMapper {

    Map<String,String> map = [
            "category":"pos",
            "pos":"category",
            "string":"word",
            "word":"string"
    ]

    String get(String name) {
        String mapped = map[name]
        if (mapped) {
            return mapped
        }
        return name
    }
}