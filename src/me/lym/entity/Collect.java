package me.lym.entity;

public class Collect {
    private int cid;
    private int uid;
    private int gid;
    private int collect;
    private int history;

    public Collect() {
    }

    public Collect(int uid, int gid, int collect, int history) {
        super();
        this.uid = uid;
        this.gid = gid;
        this.collect = collect;
        this.history = history;
    }

    public Collect(int cid, int uid, int gid, int collect, int history) {
        super();
        this.cid = cid;
        this.uid = uid;
        this.gid = gid;
        this.collect = collect;
        this.history = history;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Collect [cid=" + cid + ", uid=" + uid + ", gid=" + gid + ", collect=" + collect + ", history=" + history
                + "]";
    }
}
