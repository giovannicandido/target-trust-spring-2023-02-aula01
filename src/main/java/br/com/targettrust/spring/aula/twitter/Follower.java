package br.com.targettrust.spring.aula.twitter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Follower {
    private String id;

    @JsonProperty("nomePessoa")
    private String name;

    @JsonIgnore
    private String twitterLogin;

    public Follower(String id, String name, String twitterLogin) {
        this.id = id;
        this.name = name;
        this.twitterLogin = twitterLogin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTwitterLogin() {
        return twitterLogin;
    }

    public void setTwitterLogin(String twitterLogin) {
        this.twitterLogin = twitterLogin;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", twitterLogin='" + twitterLogin + '\'' +
                '}';
    }
}
