package jp.peranikov.puhaar.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import jp.peranikov.puhaar.R;
import jp.peranikov.puhaar.models.Photo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhotoDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PhotoDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoDetailFragment extends Fragment{

    private OnFragmentInteractionListener mListener;

    private Photo mPhoto;

    @InjectView(R.id.imageview) ImageView mImageView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PhotoDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotoDetailFragment newInstance(Photo photo) {
        PhotoDetailFragment fragment = new PhotoDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("photo", photo);
        fragment.setArguments(args);
        return fragment;
    }

    public PhotoDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPhoto = (Photo)getArguments().getSerializable("photo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        ButterKnife.inject(this, view);

        Glide.with(this).load(mPhoto.imageUrl()).into(mImageView);
        return view;
    }

    @OnClick(R.id.CloseButton)
    public void onCloseButtonPressed() {
        if (mListener != null) {
            mListener.onClosePhotoDetailFragment();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onClosePhotoDetailFragment();
    }



}
