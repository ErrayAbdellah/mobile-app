package com.example.appquiz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appquiz.R
import com.example.appquiz.model.Quiz

class QuizAdapter(
    private val quizList: List<Quiz>,
    private val onAnswerSelected: (Int, String) -> Unit, // Callback for selected answers
    private val onSubmitClicked: () -> Unit // Callback for submit button
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_QUIZ = 0
        private const val VIEW_TYPE_BUTTON = 1
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText: TextView = itemView.findViewById(R.id.questionText)
        val optionsGroup: RadioGroup = itemView.findViewById(R.id.optionsGroup)
    }

    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val submitButton: Button = itemView.findViewById(R.id.submitButton)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == quizList.size) VIEW_TYPE_BUTTON else VIEW_TYPE_QUIZ
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_QUIZ) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz, parent, false)
            QuizViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_submit_button, parent, false)
            ButtonViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is QuizViewHolder) {
            val quiz = quizList[position]
            holder.questionText.text = quiz.question

            // Clear previous RadioButtons if any
            holder.optionsGroup.removeAllViews()

            // Dynamically add RadioButtons for options
            quiz.options.forEach { option ->
                val radioButton = RadioButton(holder.itemView.context).apply {
                    text = option
                    id = View.generateViewId()
                }
                holder.optionsGroup.addView(radioButton)
            }

            // Handle user selection
            holder.optionsGroup.setOnCheckedChangeListener { _, checkedId ->
                val selectedOption = holder.optionsGroup.findViewById<RadioButton>(checkedId)?.text.toString()
                onAnswerSelected(position, selectedOption) // Pass position and selected option
            }
        } else if (holder is ButtonViewHolder) {
            holder.submitButton.setOnClickListener {
                onSubmitClicked()
            }
        }
    }

    override fun getItemCount(): Int {
        return quizList.size + 1 // Add one for the button
    }
}