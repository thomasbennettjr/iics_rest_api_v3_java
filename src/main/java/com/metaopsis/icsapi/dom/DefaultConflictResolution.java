package com.metaopsis.icsapi.dom;

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
