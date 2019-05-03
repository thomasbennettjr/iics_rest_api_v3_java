package com.metaopsis.icsapi.v3.dom;


public class Query {
    private StringBuilder query;
    private boolean _IsFirstParam = true;

    public Query()
    {
        query = new StringBuilder();
        query.append("?q=");
    }

    public void andType(Asset type, boolean isEqual)
    {
        if (!isFirstParam())
            query.append(" and ");

        query.append("type" + (isEqual ? "==" : "!=") + "'" + type.toString() + "'");
    }

    public void andLocation(String location)
    {
        if (!isFirstParam())
            query.append(" and ");

        query.append("location=='" + location + "'");
    }

    public void andUpdateTime(String tstamp, Operator operator)
    {
        if (!this.isFirstParam())
            query.append(" and ");

        query.append("updateTime" + operator.toString() + tstamp);
    }

    public void andUpdatedBy(String user, boolean isEqual)
    {
        if (!isFirstParam())
            query.append(" and ");

        query.append("updatedBy" + (isEqual ? "==" : "!=") + "'" + user + "'");
    }

    public void andTag(String tag)
    {
        if (!isFirstParam())
            query.append(" and ");

        query.append("tag=='" + tag + "'");
    }

    public void limit(int limit)
    {
        query.append("&limit=" + limit);
    }

    public void skip(int skip)
    {
        query.append("&skip=" + skip);
    }

    private boolean isFirstParam()
    {
        boolean currentState = this._IsFirstParam;
        if (this._IsFirstParam)
            this._IsFirstParam = false;

        return currentState;
    }

    @Override
    public String toString()
    {
        return this.query.toString();
    }
}
