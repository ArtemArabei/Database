package com.example.database.room

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class DeleteDialogFragment(
    private val playerDao: PlayerDao,
    private val player: Player,
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(DIALOG_TITLE_TEXT)
                .setCancelable(true)
                .setPositiveButton(POSITIVE_BUTTON_TEXT) { _, _ ->
                    playerDao.delete(player)
                    Toast.makeText(activity, TOAST_DELETE_TEXT,
                        Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(NEGATIVE_BUTTON_TEXT) { _, _ ->
                    Toast.makeText(activity, TOAST_CANCEL_TEXT,
                        Toast.LENGTH_SHORT).show()
                }
            builder.create()
        } ?: throw IllegalStateException(ILLEGAL_STATE_EXCEPTION_TEXT)
    }

    companion object {
        private const val DIALOG_TITLE_TEXT = "Delete this Snooker Player?"
        private const val POSITIVE_BUTTON_TEXT = "Delete"
        private const val NEGATIVE_BUTTON_TEXT = "Cancel"
        private const val TOAST_DELETE_TEXT = "Deleted successfully"
        private const val TOAST_CANCEL_TEXT = "Deleting cancelled"
        private const val ILLEGAL_STATE_EXCEPTION_TEXT = "Activity cannot be null"
    }
}

