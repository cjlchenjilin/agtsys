package org.agtsys.domain;

public class Account {
    private Long id;

    private Long userid;

    private Double money;

    private Double moneybak;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getMoneybak() {
        return moneybak;
    }

    public void setMoneybak(Double moneybak) {
        this.moneybak = moneybak;
    }
}