package edu.uwf.tabletopgroup.tabletop_squire;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.uwf.tabletopgroup.R;
import edu.uwf.tabletopgroup.models.Player;
import edu.uwf.tabletopgroup.models.User;
import edu.uwf.tabletopgroup.rest.TableTopKeys;

/**
 * TableTopSquire
 * JoinGameByIdDialog.java
 *
 * TODO: Write Description
 *
 * Created By Michael Kimball
 * On 4/17/16
 */
public class JoinGameByIdDialog extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_join_game_id, container, false);
        final EditText input = (EditText)v.findViewById(R.id.game_id);
        Button join = (Button)v.findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gameId = input.getText().toString();
                if(gameId.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter a game id to join a game.", Toast.LENGTH_LONG).show();
                }else{
                    Intent i = new Intent(TableTopKeys.ACTION_JOIN_GAME);
                    Player player = new Player(User.getUsername(), User.getEmail(), User.getCharacter(0));
                    i.putExtra(TableTopKeys.KEY_GAME_ID, gameId);
                    i.putExtra(TableTopKeys.KEY_PLAYER, player);
                    getActivity().sendBroadcast(i);
                }
                dismiss();
            }
        });
        return v;
    }
}