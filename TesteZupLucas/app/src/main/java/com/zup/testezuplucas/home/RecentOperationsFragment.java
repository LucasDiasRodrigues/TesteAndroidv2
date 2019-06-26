package com.zup.testezuplucas.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zup.testezuplucas.R;
import com.zup.testezuplucas.model.Operation;
import com.zup.testezuplucas.util.PreferencesController;

import java.util.ArrayList;


interface ListOperationsInterface {
    void listOperationsSuccess(ArrayList<Operation> operations);

    void listOperationsFailure();
}

public class RecentOperationsFragment extends Fragment implements ListOperationsInterface {

    private RecyclerView recyclerView;
    private ArrayList<Operation> operations;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_recent_operations, container, false);

        recyclerView = (RecyclerView) fragment.findViewById(R.id.operationsList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        OperationsFragmentController controller = new OperationsFragmentController(this);
        controller.getOperarionsList(PreferencesController.getInstance(getContext()).getLoggedUser());


        return fragment;
    }

    public void dismissProgress() {
        View view = getView();
        if (view != null) {
            getView().findViewById(R.id.operationsProgress).setVisibility(View.GONE);
            getView().findViewById(R.id.operationsContainer).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void listOperationsSuccess(ArrayList<Operation> operations) {
        this.operations = operations;
        recyclerView.setAdapter(new OperationsListAdapter(this.operations));
        dismissProgress();
    }

    @Override
    public void listOperationsFailure() {
        Toast.makeText(getActivity(), "Ops... deu ruim. Tente novamente", Toast.LENGTH_SHORT).show();
        dismissProgress();
    }
}
