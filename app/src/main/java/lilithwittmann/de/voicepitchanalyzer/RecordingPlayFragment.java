package lilithwittmann.de.voicepitchanalyzer;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lilithwittmann.de.voicepitchanalyzer.utils.PitchCalculator;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordingPlayFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public RecordingPlayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sectionNumber
     * @return A new instance of fragment RecordingOverviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordingPlayFragment newInstance(int sectionNumber) {
        RecordingPlayFragment fragment = new RecordingPlayFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recording_play, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (RecordViewActivity.currentRecord != null) {
            double average = RecordViewActivity.currentRecord.getRange().getAvg();

            ((TextView) view.findViewById(R.id.average)).setText(String.format("%sHz", Math.round(average)));
            ((TextView) view.findViewById(R.id.min_avg)).setText(String.format("%sHz", Math.round(RecordViewActivity.currentRecord.getRange().getMin())));
            ((TextView) view.findViewById(R.id.max_avg)).setText(String.format("%sHz", Math.round(RecordViewActivity.currentRecord.getRange().getMax())));

            if (average < PitchCalculator.minFemalePitch) {
                ((TextView) view.findViewById(R.id.personal_range)).setText(getResources().getString(R.string.male));
            } else if (average > PitchCalculator.maxMalePitch) {
                ((TextView) view.findViewById(R.id.personal_range)).setText(getResources().getString(R.string.female));
            } else {
                ((TextView) view.findViewById(R.id.personal_range)).setText(getResources().getString(R.string.in_between));
            }
        }
    }
}