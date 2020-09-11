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
public class TabFragment1 extends Fragment {
    private LearnAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    private List<RetroLearn> mWordList;
    /*private final LinkedList<String> mWordList = new LinkedList<>();*/
//    private RecyclerView mRecyclerView;
//    private LearnAdapter mAdapter;

    public TabFragment1() {
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
        final View learnView = inflater.inflate(R.layout.tab_fragment1, container, false);

            progressDoalog = new ProgressDialog(getActivity());
            progressDoalog.setMessage("Loading....");
            progressDoalog.show();

            /*Create handle for the RetrofitInstance interface*/
            VerbService service = RetrofitClientInstance.getRetrofitInstance().create(VerbService.class);
            Call<List<RetroLearn>> call = service.getHours();
            call.enqueue(new Callback<List<RetroLearn>>() {
                @Override
                public void onResponse(Call<List<RetroLearn>> call, Response<List<RetroLearn>> response) {
                    progressDoalog.dismiss();
                    List<RetroLearn> mWordList = response.body();
//                    System.out.println(response.body());
                    recyclerView = learnView.findViewById(R.id.learn_recyclerview);
                    adapter = new LearnAdapter(getActivity(), mWordList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<RetroLearn>> call, Throwable t) {
                    progressDoalog.dismiss();
                    Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_LONG).show();

                }
            });

            return learnView;


    }
}