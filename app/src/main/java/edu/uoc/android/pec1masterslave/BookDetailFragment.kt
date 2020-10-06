package edu.uoc.android.pec1masterslave


import android.R.drawable
import android.os.Bundle
import android.text.Html
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.ui.tooling.preview.Preview
import edu.uoc.android.pec1masterslave.model.BookModel
import java.io.File


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [BookDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookDetailFragment : Fragment() {
    // Rename and change types of parameters
    private lateinit var item: BookModel.BookItem
    private var param2: String? = null

    // get the parameters through the interface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = requireArguments().getSerializable(BookDetailFragment.ARG_PARAM1) as BookModel.BookItem
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_book_detail, container, false)

        item.let {


            /* Here we substitute the layout by a jetpack-compose stile
                fragmentView.findViewById<ImageView>(R.id.fragment_book_image).setImageResource(resID)
                fragmentView.findViewById<TextView>(R.id.fragment_book_author).text = item!!.autor
                fragmentView.findViewById<TextView>(R.id.fragment_book_date).text = dateFormat
                fragmentView.findViewById<TextView>(R.id.fragment_book_description).text = item!!.descripccion
                */


            (fragmentView as ViewGroup).setContent {
                Hello("Jetpack Compose", item)
            }
        }

        return fragmentView
    }

    @Composable
    fun Hello(name: String, item:BookModel.BookItem) = MaterialTheme {
        // create an integer which contains the associated jpg
        val resID = resources.getIdentifier(
            "cartel" + item.identificador.toString(),
            "drawable",
            activity?.packageName
        )
        val dateFormat = DateFormat.format("dd/MM/yyyy", item.fechaPublicacion)

        Log.d("cfauli", "drawable " + resID)
        ConstraintLayout {
            val (image, text) = createRefs()
            Row() {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    val imageModifier = Modifier

                    //.preferredHeight(180.dp)
                    //.fillMaxWidth()

                    Image(
                        imageResource(id = resID), modifier = imageModifier,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.preferredHeight(16.dp))
                    Text(item.descripccion!!)
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(item.titulo!!)
                    Text(dateFormat.toString())
                }
            }
        }
    }

    /*@Preview
    @Composable
    fun DefaultPreview() {
        Hello("Android", BookModel.BookItem(0))
    }*/

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




