package com.vijaygaike.unitconverter.ui.speed;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vijaygaike.unitconverter.DBHandler;
import com.vijaygaike.unitconverter.R;
import com.vijaygaike.unitconverter.databinding.FragmentSpeedBinding;
import com.vijaygaike.unitconverter.ui.conversionFactors.speedConversionFactor;

public class SpeedFragment extends Fragment {

    private FragmentSpeedBinding binding;
    Spinner Spinner;
    Spinner Spinner2;
    EditText userInput;
    TextView result;
    Button calc;
    String unitIn;
    String unitOut;
    String input;
    String m;
    String res;
    private DBHandler dbHandler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSpeedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//code here
        Spinner = root.findViewById(R.id.speedSpinner);
        Spinner2 = root.findViewById(R.id.speedSpinner2);
        userInput = root.findViewById(R.id.inputSpeed);
        result = root.findViewById(R.id.outputSpeed);
        calc = root.findViewById(R.id.calculateButton);
        dbHandler = new DBHandler(getContext());

        Spinner.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(), R.array.speed, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner.setAdapter(adapter);

        Spinner2.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(getActivity(), R.array.speed, android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner2.setAdapter(adapter1);

        speedConversionFactor getResult = new speedConversionFactor();

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    userInput.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    unitIn = Spinner.getSelectedItem().toString();
                    unitOut = Spinner2.getSelectedItem().toString();
                    input = userInput.getText().toString();
                    m = String.valueOf(getResult.convertSpeedToMps(input, unitIn));
                    res = String.valueOf(getResult.convertMpsToUser(m, unitOut));
                    Log.d("VIJAY", "input : " + res);
                    result.setText((CharSequence) res);
                    String history_expression = userInput.getText().toString() + " " + unitIn + " = " + result.getText().toString() + " " + unitOut;                    boolean i = dbHandler.addNewCourse(history_expression);
                    if (i) Log.d("VIJAY", "add_expression: history added");
                    else Log.d("VIJAY", "add_expression: error in adding history");
                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "Please enter a value...", Toast.LENGTH_SHORT).show();
                    Log.d("VIJAY", "onEmptyInput: no input given");
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}