package com.example.vktask;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ContactUser extends AppCompatActivity implements init {

    private ImageButton backToHome;
    private ContactsUsersAdapter contactsUsersAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Cusr> usersList;
    private LinearLayoutManager linearLayoutManager;
    private int[] image = {R.drawable.ic_user1, R.drawable.ic_user2,
            R.drawable.ic_user3, R.drawable.ic_user4, R.drawable.ic_user5,
            R.drawable.ic_user6, R.drawable.ic_user7, R.drawable.ic_user8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_users);

        usersList = generateUserList();
        initialise();

        backToHome.setOnClickListener(v -> {
            if (savedInstanceState == null) {
                Intent backToMainScreen = new Intent(this, MainActivity.class);
                backToMainScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backToMainScreen);
                finishAndRemoveTask();
            }
        });
    }

    @Override
    public void initialise() {

        backToHome = findViewById(R.id.home);
        recyclerView = findViewById(R.id.activity_user_contact);

        contactsUsersAdapter = new ContactsUsersAdapter(usersList);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(contactsUsersAdapter);
    }

    private ArrayList<Cusr> generateUserList() {
        ArrayList<Cusr> human = new ArrayList<>();

        human.add(new Cusr("Wilson Pickett", 1111, R.drawable.ic_user1));
        human.add(new Cusr("Test nickname to make sure we can cut off our string in case of long name", 4356, R.drawable.ic_user2));
        human.add(new Cusr("Sam Cooke", 9994, R.drawable.ic_user3));
        human.add(new Cusr("Jackie Wilson", 3471, R.drawable.ic_user4));
        human.add(new Cusr("Otis Redding", 2234, R.drawable.ic_user5));
        human.add(new Cusr("Looking Glass", 1234, R.drawable.ic_user6));
        human.add(new Cusr("Glen Campbell", 5838, R.drawable.ic_user7));
        human.add(new Cusr("Elvin Bishop", 4948, R.drawable.ic_user8));

        return human;
    }
}
