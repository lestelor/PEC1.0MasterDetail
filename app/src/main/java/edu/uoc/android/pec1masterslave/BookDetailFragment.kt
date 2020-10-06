package edu.uoc.android.pec1masterslave


import android.R.drawable
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import edu.uoc.android.pec1masterslave.model.BookModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private var item: String? = null

/**
 * A simple [Fragment] subclass.
 * Use the [BookDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookDetailFragment : Fragment() {
    // Rename and change types of parameters
    private var item: BookModel.BookItem? = null
    private var param2: String? = null

    // get the parameters through the interface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = arguments!!.getSerializable(BookDetailFragment.ARG_PARAM1) as BookModel.BookItem?
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_book_detail, container, false)

        item?.let {
            // create an integuer wich contains the associated jpg
            val resID = resources.getIdentifier(
                "cartel" + item!!.identificador.toString(),
                "drawable",
                activity?.packageName
            )
            // create view
            val dateFormat = DateFormat.format("dd/MM/yyyy", item?.fechaPublicacion)
            Log.d("cfauli", "drawable " + resID)
            fragmentView.findViewById<ImageView>(R.id.fragment_book_image).setImageResource(resID)
            fragmentView.findViewById<TextView>(R.id.fragment_book_author).text = item!!.autor
            fragmentView.findViewById<TextView>(R.id.fragment_book_date).text = dateFormat
            fragmentView.findViewById<TextView>(R.id.fragment_book_description).text = item!!.descripccion
        }
        return fragmentView
    }

    companion object {
        const val ARG_PARAM1 = "param1"
        const val ARG_PARAM2 = "param2"
        /* Use this factory method to create a new instance of
        * this fragment using the provided parameters.
        *
        * @param param1 Parameter 1.
        * @param param2 Parameter 2.
        * @return A new instance of fragment BookDetailFragment.
        */
        /*
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
         */
    }

}