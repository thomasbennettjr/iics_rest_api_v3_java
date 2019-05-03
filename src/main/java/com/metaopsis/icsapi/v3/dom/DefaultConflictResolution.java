package com.metaopsis.icsapi.v3.dom;

public enum DefaultConflictResolution {
    OVERWRITE {
        public String toString() {
            return "OVERWRITE";
        }
    },
    REUSE {
        public String toString() {
            return "REUSE";
        }
    }
}
