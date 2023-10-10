package in.gurulabs.timecare.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.gurulabs.timecare.R;
import in.gurulabs.timecare.adapter.TimeTableListAdapter;
import in.gurulabs.timecare.database.TimeTable;
import in.gurulabs.timecare.database.TimeTableViewModel;

public class Tasks extends Fragment {


    private static final String TAG = "Mon";

    private RecyclerView recyclerViewView;
    private LinearLayoutManager manager;
    RelativeLayout empty_view;
    TimeTableListAdapter timeTableListAdapter;
    TimeTableViewModel timeTableViewModel;

    AutoCompleteTextView search_box;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_days, container, false);

        empty_view = view.findViewById(R.id.empty_view);
        recyclerViewView = view.findViewById(R.id.recyclerView);

        recyclerViewView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext());
        recyclerViewView.setLayoutManager(manager);




        timeTableViewModel = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(TimeTableViewModel.class);
        timeTableViewModel.getThursdayTimeTable().observe(getViewLifecycleOwner(), new Observer<List<TimeTable>>() {
            @Override
            public void onChanged(List<TimeTable> timeTableList) {


                if (!timeTableList.isEmpty()) {
                    recyclerViewView.setVisibility(View.VISIBLE);
                    empty_view.setVisibility(View.GONE);


                } else {
                    recyclerViewView.setVisibility(View.GONE);
                    empty_view.setVisibility(View.VISIBLE);
                }


                timeTableListAdapter = new TimeTableListAdapter(getActivity(), timeTableList);
                recyclerViewView.setAdapter(timeTableListAdapter);


            }
        });

        search_box = view.findViewById(R.id.search_view);
        search_box.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    timeTableViewModel.getSearchedTimeTable(String.valueOf(search_box.getText())).observe(getViewLifecycleOwner(), new Observer<List<TimeTable>>() {
                        @Override
                        public void onChanged(List<TimeTable> timeTables) {

                            if (!timeTables.isEmpty()) {
                                recyclerViewView.setVisibility(View.VISIBLE);
                                empty_view.setVisibility(View.GONE);


                            } else {
                                recyclerViewView.setVisibility(View.GONE);
                                empty_view.setVisibility(View.VISIBLE);
                            }


                            timeTableListAdapter = new TimeTableListAdapter(getActivity(), timeTables);
                            recyclerViewView.setAdapter(timeTableListAdapter);

                        }
                    });










                    return true;
                }
                return false;
            }
        });

        return view;
    }



}