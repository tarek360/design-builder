package co.designbuilder.presentation.ui

import android.content.Context
import android.content.Intent

class Utility {


  companion object {

    fun startShare(context: Context, title: String, text: String, subject: String) {
      val intent = Intent(Intent.ACTION_SEND)
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      intent.type = "text/plain"
      intent.putExtra(Intent.EXTRA_TEXT, text)
      intent.putExtra(Intent.EXTRA_SUBJECT, subject)
      context.startActivity(Intent.createChooser(intent, title))
    }
  }
}