package com.metaopsis.icsapi.dom;

public enum Operator {
    LT {
        public String toString() {
            return "<";
        }
    },
    LTE {
        public String toString() {
            return "<=";
        }
    },
    EQ {
        public String toString() {
            return "==";
        }
    },
    GTE {
        public String toString() {
            return "=>";
        }
    },
    GT {
        public String toString() {
            return ">";
        }
    },
    NE {
        public String toString() {
            return "!=";
        }
    }
}
