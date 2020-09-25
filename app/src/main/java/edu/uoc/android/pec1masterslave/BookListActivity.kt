package edu.uoc.android.pec1masterslave

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        viewManager = LinearLayoutManager(this)
        var w900dp = false
        if (findViewById<NestedScrollView>(R.id.book_detail_fragment) != null) {
            w900dp = true
        }
        viewAdapter = MyAdapter(this,
            arrayOf(1,2,3,4,5,6,7,8,9,10), w900dp)

        recyclerView = findViewById<RecyclerView>(R.id.book_list).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

    }

    class MyAdapter(private val parentActivity: BookListActivity,
                    private val values: Array<Int>,
                    private val w900dp:Boolean):
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.findViewById(R.id.id_text)
            val contentView: TextView = view.findViewById(R.id.content)

        }
        data class DummyItem(val id: String, val content: String, val details: String) {
            override fun toString(): String = content
        }

        private val onClickListener: View.OnClickListener = View.OnClickListener { v ->
            Log.d("cfauli", "onClickListener")
            val item:String = v.tag as String
            if (w900dp) {
                Log.d("cfauli", "onClickListener w900dp")
                val fragment = BookDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(BookDetailFragment.ARG_PARAM1, item)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.book_detail_fragment, fragment)
                    .commit()
            } else {
                Log.d("cfauli", "onClickListener portrait")
                val intent = Intent(v.context, BookDetailActivity::class.java).apply {
                    putExtra(BookDetailFragment.ARG_PARAM1, item)
                }
                v.context.startActivity(intent)
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            Log.d("cfauli", "onCreateViewHolder")
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.book_list_content, parent, false)
            return ViewHolder(view)
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = values.size

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Log.d("cfauli", "onBindViewHolder")
            val item = values[position].toString()
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.idView.text = item
            holder.contentView.text = "Item " + item
            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

    }


}


