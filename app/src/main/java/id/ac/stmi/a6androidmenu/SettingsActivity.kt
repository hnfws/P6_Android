package id.ac.stmi.a6androidmenu

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SettingsActivity : AppCompatActivity() {

    private lateinit var tvContextTarget: TextView
    private lateinit var btnShowPopup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvContextTarget = findViewById(R.id.tv_context_target)
        registerForContextMenu(tvContextTarget)

        btnShowPopup = findViewById(R.id.btn_show_popup)
        btnShowPopup.setOnClickListener { view ->
            showPopupMenu(view)
        }
    }

    // Popup Menu
    private fun showPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.menu_popup, popup.menu)

        popup.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_share -> {
                    showToast("Popup: Bagikan dipilih")
                    true
                }

                R.id.action_copy -> {
                    showToast("Popup: Copy dipilih")
                    true
                }

                else -> false
            }
        }
        popup.show()
    }

    // 1. Option Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    // 2. Helper Toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // 3. Context Menu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context, menu)
        menu?.setHeaderTitle("pilih aksi")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                showToast("Context: Edit Dipilih")
                true
            }

            R.id.action_delete -> {
                showToast("Context: Hapus Dipilih")
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                showToast("Options: Search dipilih")
                true
            }

            R.id.action_folder -> {
                showToast("Options: Folder dipilih")
                true
            }

            R.id.action_share -> {
                showToast("Options: Share dipilih")
                true
            }

            R.id.action_feedback -> {
                showToast("Options: Feedback dipilih")
                true
            }

            R.id.action_help -> {
                showToast("Options: Help dipilih")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
