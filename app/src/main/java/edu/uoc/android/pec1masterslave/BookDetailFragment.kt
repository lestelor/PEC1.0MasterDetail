package edu.uoc.android.pec1masterslave


import android.app.Notification
import android.graphics.Paint
import android.os.Bundle
import android.text.format.DateFormat
import android.text.style.ParagraphStyle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.StackView
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContainerAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.ui.tooling.preview.Preview
import edu.uoc.android.pec1masterslave.model.BookModel

import java.util.*


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
        //val fragmentView = inflater.inflate(R.layout.fragment_book_detail, container, false)

        item.let {


            /* Here we substitute the layout by a jetpack-compose stile
                fragmentView.findViewById<ImageView>(R.id.fragment_book_image).setImageResource(resID)
                fragmentView.findViewById<TextView>(R.id.fragment_book_author).text = item!!.autor
                fragmentView.findViewById<TextView>(R.id.fragment_book_date).text = dateFormat
                fragmentView.findViewById<TextView>(R.id.fragment_book_description).text = item!!.descripccion
                */

            return ComposeView(requireContext()).apply {
                setContent {
                    Hello(item)
                }
            }

            /*(fragmentView as ViewGroup).setContent {
                Hello(item)
            }*/
        }

        //return fragmentView
    }

    @Composable
    fun Hello(item: BookModel.BookItem) = MaterialTheme {
        // create an integer which contains the associated jpg
        val setPadding = 16.dp

        val resID = resources.getIdentifier(
            "cartel" + item.identificador.toString(),
            "drawable",
            activity?.packageName
        )
        val dateFormat = DateFormat.format("dd/MM/yyyy", item.fechaPublicacion)

        Log.d("cfauli", "drawable " + resID)

        ConstraintLayout() {
            val (image, column, text, text2) = createRefs()
            val imageModifier = Modifier
                .padding(16.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            Image(
                imageResource(id = resID), modifier = imageModifier,
                contentScale = ContentScale.Crop
            )

            Column(Modifier
                .padding(setPadding)
                .constrainAs(column) {
                    start.linkTo(image.end)
                    top.linkTo(parent.top)
                }) {
                Text(item.titulo.toString(), style = MaterialTheme.typography.h1)
                Text(item.autor.toString())
                Text(item.urlPortada.toString())
            }

            Text(
                modifier = Modifier
                    .padding(setPadding)
                    .constrainAs(text) {
                        start.linkTo(image.end)
                        bottom.linkTo(image.bottom)
                    },
                text = dateFormat.toString()
            )

            Text(
                modifier = Modifier
                    .padding(setPadding)
                    .constrainAs(text2) {
                        start.linkTo(parent.start)
                        top.linkTo(image.bottom)
                    },
                text = item.descripccion.toString(),
                style = MaterialTheme.typography.body1
            )

        }


    }

    @Preview ("BookDetailFragment")
    @Composable
    fun Preview() {
       Hello(BookModel.book1)
    }

    @Preview ("BookDetailFragment")
    @Composable
    fun Example() {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = 2.dp
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth(),
            ) {
                val(column, divider, text) = createRefs()
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .constrainAs(column){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Code")
                    Text(text = "item.code")
                }
                Spacer(
                    modifier = Modifier
                        .preferredWidth(1.dp)
                        .background(color = MaterialTheme.colors.onSurface.copy(0.12f))
                        .constrainAs(divider){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(column.end)
                        }
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 34.dp)
                        .constrainAs(text){
                            start.linkTo(divider.end)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    text = "item.name"
                )
            }
        }
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





