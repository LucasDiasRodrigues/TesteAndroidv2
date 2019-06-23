package com.zup.testezuplucas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecentOperationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private OperationsListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_recent_operations, container, false);

        recyclerView = (RecyclerView) fragment.findViewById(R.id.operationsList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //teste
        ArrayList<Operation> operations = new ArrayList<>();
        for(int i=0; i <10; i++){
            operations.add(new Operation("Pagamento", "Conta de agua", "17/12/1992", "R$136,00"));
        }
        //

        adapter = new OperationsListAdapter(operations);
        recyclerView.setAdapter(adapter);


        return fragment;
    }
}
