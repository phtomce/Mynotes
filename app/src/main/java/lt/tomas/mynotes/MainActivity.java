package lt.tomas.mynotes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "my_notes_app_tag";
    private ArrayAdapter<Note> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView simpleListView = findViewById(R.id.simpleListView);
        FloatingActionButton addNoteFab = findViewById(R.id.addNoteFab);

        Repository.generateNoteList();

        setUpListView(simpleListView);
        setClickListenerOfListView(simpleListView);
        setLongClickListenerOfListView(simpleListView);
        setClickListenerOfAddNoteFab(addNoteFab);
    }

    private void setUpListView(ListView simpleListView) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Repository.getNotes()
        );
        simpleListView.setAdapter(adapter);
    }
    private void setClickListenerOfListView(ListView simpleListView) {
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i(TAG, "onItemClick: " + Repository.getNotes().get(position));
            }
        };
        simpleListView.setOnItemClickListener(listener);
    }
    private void setLongClickListenerOfListView(ListView simpleListView) {
        simpleListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialogIfRequireToDeleteNote(i);
                return true;
            }
        });
    }
    private void showDialogIfRequireToDeleteNote(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        DialogInterface.OnClickListener positiveClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Repository.removeNote(position);
                        adapter.notifyDataSetChanged();
                    }
                };
        builder
                .setMessage("Ar tikrai norite istrinti?")
                .setPositiveButton("Yes", positiveClickListener)
                .setNegativeButton("No", null)
                .show();
    }

    private void setClickListenerOfAddNoteFab(FloatingActionButton addNoteFab) {

        addNoteFab.setOnClickListener(
                view -> {
                    int lastItem = Repository.getNotes().size()-1;
                    int i = Repository.getNotes().get(lastItem).getId() + 1;

                    Repository.addNote(
                            new Note(
                                    i,
                                    "Name_" + i,
                                    "Some note text_" + i
                            )
                    );

                    adapter.notifyDataSetChanged();
                    displaySnackBarAddNote(addNoteFab);
                }
        );
    }

    private void displaySnackBarAddNote(FloatingActionButton addNoteFab) {
        Snackbar
                .make(addNoteFab,"pridetas naujas irasas", Snackbar.LENGTH_LONG)
                .show();
    }
}