package edu.uoc.android.pec1masterslave

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NavUtils
import edu.uoc.android.pec1masterslave.model.BookModel

class BookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        val toolbar = findViewById<Toolbar>(R.id.detail_toolbar)
        setSupportActionBar(toolbar)
        val bookItem = intent.getSerializableExtra(BookDetailFragment.ARG_PARAM1) as BookModel.BookItem
        val actionBarTittle = bookItem.titulo
        actionBar?.title =actionBarTittle
        supportActionBar?.title = actionBarTittle  // provide compatibility to all the versions

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don"t need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            val bundle = Bundle()
            bundle.putSerializable(BookDetailFragment.ARG_PARAM1, bookItem)
            val fragment = BookDetailFragment().apply {
                arguments = bundle
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.book_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {

                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                NavUtils.navigateUpTo(this, Intent(this, BookListActivity::class.java))

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}