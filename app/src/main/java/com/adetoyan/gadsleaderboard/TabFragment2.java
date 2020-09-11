package com.adetoyan.gadsleaderboard;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment2 extends Fragment {
    private SkillAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    private List<RetroSkill> mWordList;

//    private final LinkedList<String> iWordList = new LinkedList<>();
//    private RecyclerView iRecyclerView;
//    private SkillAdapter iAdapter;

    public TabFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View skillView = inflater.inflate(R.layout.tab_fragment2, container, false);

        /*for (int i = 0; i < 20; i++) {
            iWordList.addLast("Skill " + i);
        }
        // Get a handle to the RecyclerView.
        iRecyclerView = skillView.findViewById(R.id.skill_recyclerview);
// Create an adapter and supply the data to be displayed.
        iAdapter = new SkillAdapter(getActivity(), iWordList);
// Connect the adapter with the RecyclerView.
        iRecyclerView.setAdapter(iAdapter);
// Give the RecyclerView a default layout manager.
        iRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/

        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        VerbService service = RetrofitClientInstance.getRetrofitInstance().create(VerbService.class);
        Call<List<RetroSkill>> call = service.getScore();
        call.enqueue(new Callback<List<RetroSkill>>() {
            @Override
            public void onResponse(Call<List<RetroSkill>> call, Response<List<RetroSkill>> response) {
                progressDoalog.dismiss();
                List<RetroSkill> mWordList = response.body();
//                    System.out.println(response.body());
                recyclerView = skillView.findViewById(R.id.skill_recyclerview);
                adapter = new SkillAdapter(getActivity(), mWordList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<RetroSkill>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_LONG).show();

            }
        });

        return skillView;
    }

}
