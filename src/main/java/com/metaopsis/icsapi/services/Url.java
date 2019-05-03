package com.metaopsis.icsapi.services;

public enum Url {
    LOGIN_NA {
        public String toString() {
            return "https://dm-us.informaticacloud.com/saas/public/core/v3/login";
        }
    },
    LOGIN_EMEA {
        public String toString() {
            return "https://dm-eu.informaticacloud.com/saas/public/core/v3/login";
        }
    },
    LOGIN_APAC {
        public String toString() {
            return "https://dm-ap.informaticacloud.com/saas/public/core/v3/login";
        }
    },
    LOGOUT_NA {
        public String toString() {
            return "https://dm-us.informaticacloud.com/saas/public/core/v3/logout";
        }
    },
    LOGOUT_EMEA {
        public String toString() {
            return "https://dm-eu.informaticacloud.com/saas/public/core/v3/logout";
        }
    },
    LOGOUT_APAC {
        public String toString() {
            return "https://dm-ap.informaticacloud.com/saas/public/core/v3/logout";
        }
    }
}
