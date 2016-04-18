package edu.uwf.tabletopgroup.models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import edu.uwf.tabletopgroup.rest.TableTopKeys;

/**
 * TableTopSquire
 * Player.java
 *
 * TODO: Write Description
 *
 * Created By Michael Kimball
 * On 4/16/16
 */
public class Player implements Parcelable {
    private String name;
    private String email;
    private Character character;

    public Player(String name, String email){
        this.name = name;
        this.email = email;
        this.character = null;
    }
    public Player(String name, String email, Character character){
        this.name = name;
        this.email = email;
        this.character = character;
    }

    public Player(Player player){
        this.name = player.getName();
        this.email = player.getEmail();
        if(player.getCharacter() != null)
            this.character = new Character(player.character);
    }

    public Player(Parcel in){
        String[] strings = new String[2];
        in.readStringArray(strings);
        setName(strings[0]);
        setEmail(strings[1]);
        setCharacter((Character)in.readValue(Character.class.getClassLoader()));
    }

    public Player(JSONObject object) throws JSONException {
        name = object.getString(TableTopKeys.KEY_NAME);
        email = object.getString(TableTopKeys.KEY_EMAIL);
        JSONObject characterJSON = object.getJSONObject(TableTopKeys.KEY_CHARACTER);
        character = new Character(characterJSON);
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        String[] strings = new String[]{name, email};
        dest.writeStringArray(strings);
        dest.writeValue(character);
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>(){

        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(TableTopKeys.KEY_NAME, name);
        object.put(TableTopKeys.KEY_EMAIL, email);
        if(character != null)
            object.put(TableTopKeys.KEY_CHARACTER, character.toJSON());
        return object;
    }
}