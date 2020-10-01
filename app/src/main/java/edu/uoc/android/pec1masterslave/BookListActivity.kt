package edu.uoc.android.pec1masterslave

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import edu.uoc.android.pec1masterslave.Tools.BlurBuilder
import edu.uoc.android.pec1masterslave.model.BookModel


class BookListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var sortedItems: MutableList<BookModel.BookItem> = mutableListOf()
    private var w900dp = false
    private var blurredBitmap: MutableList<Bitmap> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.M) override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)


        val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        //Set the action bar title, subtitle and elevation
        actionBar?.title = "PEC1"
        supportActionBar?.title = "PEC1"  // provide compatibility to all the versions


        if (findViewById<NestedScrollView>(R.id.book_detail_fragment) != null) {
            w900dp = true
            viewManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        } else  {
            viewManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }



        BookModel.ITEM.add(0, BookModel.book1)
        BookModel.ITEM.add(1, BookModel.book2)
        BookModel.ITEM.add(2, BookModel.book3)
        BookModel.ITEM.add(3, BookModel.book4)
        BookModel.ITEM.add(4, BookModel.book5)
        BookModel.ITEM.add(5, BookModel.book6)
        BookModel.ITEM.add(6, BookModel.book7)
        BookModel.ITEM.add(7, BookModel.book8)
        BookModel.ITEM.add(8, BookModel.book9)

        for (i in 0..8) {
            val resID = resources.getIdentifier(
                "cartel" + i.toString(), "drawable", this.packageName
            )
            Log.d("cfauli", "bitmap " + resID)
            val originalBitmap = BitmapFactory.decodeResource(resources, resID)
            Log.d("cfauli", "bitmap " + originalBitmap)
            blurredBitmap.add(i, BlurBuilder.blur(this, originalBitmap))
        }

        sortedItems = BookModel.ITEM


        viewAdapter = MyAdapter(
            this, sortedItems, blurredBitmap, w900dp
        )



        recyclerView = findViewById<RecyclerView>(R.id.book_list).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            itemAnimator = DefaultItemAnimator()
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter


        }

    }

    class MyAdapter(
        private val parentActivity: BookListActivity, private var values: MutableList<BookModel.BookItem>, private var blurredBitmap: MutableList<Bitmap>, private val w900dp: Boolean
    ) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var idView: TextView = view.findViewById(R.id.id_text)
            var contentView: TextView = view.findViewById(R.id.content)
            var imageView: ImageView = view.findViewById(R.id.id_image)
        }

        private val onClickListener: View.OnClickListener = View.OnClickListener { v ->
            Log.d("cfauli", "onClickListener item" + v.tag)
            val item = v.tag as Int
            if (w900dp) {
                val bundle = Bundle()
                val obj = values[item]
                bundle.putSerializable(BookDetailFragment.ARG_PARAM1, obj)
                Log.d("cfauli", "onClickListener w900dp")
                val fragment = BookDetailFragment().apply {
                    arguments = bundle
                }
                parentActivity.supportFragmentManager.beginTransaction().replace(R.id.book_detail_fragment, fragment).addToBackStack(null).commit()
            } else {
                Log.d("cfauli", "onClickListener portrait " + item)

                val intent = Intent(v.context, BookDetailActivity::class.java).apply {
                    putExtra(BookDetailFragment.ARG_PARAM1, values[item])
                }
                v.context.startActivity(intent)
            }
        }

        override fun getItemViewType(position: Int): Int {
            // Necessary to inform viewholder on the position, otherwise viewtype:int always 0
            return position
        }


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            Log.d("cfauli", "onCreateViewHolder viewType " + viewType)
            val view: View = if (viewType % 2 == 0) {
                LayoutInflater.from(parent.context).inflate(R.layout.book_list_content, parent, false)
            } else {
                LayoutInflater.from(parent.context).inflate(R.layout.book_list_content_grey, parent, false)
            }
            return ViewHolder(view)
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = values.size

        // Replace the contents of a view (invoked by the layout manager)

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.idView.text = values[position].titulo
            holder.contentView.text = values[position].autor

            val resID = holder.contentView.resources.getIdentifier(
                "cartel" + values[position]!!.identificador.toString(), "drawable", parentActivity?.packageName
            )
            holder.imageView.setImageResource(resID)
            holder.imageView.adjustViewBounds
            if(!w900dp) {
                holder.imageView.alpha = 0.5f
                //holder.imageView.setImageBitmap(blurredBitmap[position])
            }

            with(holder.itemView) {
                tag = position
                setOnClickListener(onClickListener)
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.menu_name -> {
                Log.d("cfauli", "onOptionsItemSelected titulo ")
                sortedItems.sortBy { it.titulo }
                viewAdapter.notifyDataSetChanged()

            }
            R.id.menu_author -> {
                Log.d("cfauli", "onOptionsItemSelected autor")
                sortedItems.sortBy { it.autor }
                viewAdapter.notifyDataSetChanged();
            }


        }


        return super.onOptionsItemSelected(item)
    }



}


