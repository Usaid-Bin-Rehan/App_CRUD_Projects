/*
KOTLIN SYNTAX FOR JAVA / CPP Dev

VAR & CONST:

var var_name : DataType // VAR
val val_name : DataType // CONST


FUNCTIONS:

fun myFunction(x: Int): Int // Defined return-type
fun myFunction(x: Int, y: Int) = x + y // Auto return-type


RANGE:

1..5


ARRAYS:

val fruits = arrayOf <String> ("Apple", "Mango", "Banana", "Orange") // Data-Type is optional
println( fruits.get(0) )
fruits.set(3, "Guava")
println(fruits.size)

for(item in fruits){ println(item) }

val result = fruits.drop(2) // drops first two elements.
for(item in result){ println( item ) }

val distinct = fruits.distinct()
for(item in distinct){ println(item) }

if("Mango" in fruits){ println("Exists") }
else{ println("NOT Exist") }

val fruits = arrayOf <String> ()
println( fruits.isEmpty() )


STRING TEMPLATES:

var Name = "Doe"
println("My name is $Name")

var txt = "Please locate where 'locate' occurs!"
println(txt.indexOf("locate"))  // Outputs 7


WHEN:

val result = when (day) {
     1 -> "Monday"
     2 -> "Tuesday"
     3 -> "Wednesday"
     4 -> "Thursday"
     5 -> "Friday"
     6 -> "Saturday"
     7 -> "Sunday"
     else -> "Invalid day."
   }


COLLECTIONS:

List	listOf() | listOf<T>()
Map	mapOf()
Set	setOf()

List	ArrayList<T>() | arrayListOf() | mutableListOf()
Map	HashMap | hashMapOf() | mutableMapOf()
Set	hashSetOf()

List is ordered
Set is unordered


COLLECTION METHODS:

val theList = listOf("one", "two", "three", "four")
val itr = theList.listIterator() 
while (itr.hasNext()) { println( itr.next() ) }

val resultList = theList.slice(2..4)

val theList = listOf(10, 20, 30, 31, 40, 50, -1, 0)
val resultList = theList.filter{ it > 30 }

val resultList = theList.groupBy{ it % 3 } // [31, 40, 50, -1, 0]

val resultList = theList.map{ it / 3 } // [3, 4, 10, 10, 13, 3, -1, 0]

val resultList = theList.chunked(3) // [[10, 12, 30], [31, 40, 9], [-3, 0]]


CONSTRUCTOR:

class ClassName(var brand: String, var model: String, var year: Int)
*/



/*
	Reference: https://www.youtube.com/watch?v=BBWyXo-3JGQ
	Android activity contains many views
	AppCompActivity gives behaviour of real activity
	RecyclerView Adapter: Class in which we define the layout we want to use for recycler view items
	Need to define a list with data that populates the items of recycler view along with logic of how to populate it with data
	Create a new kotlin file in same directory as main
	Create "data class Todo" whose primary purpose is to hold data
*/ 



/*
	Todo.kt:
	
	package com.example.todolist
	data class Todo(
		val title: String,
		val isChecked: Boolean = false,
	) // will pass bunch of todo items to ViewAdapter
*/



TodoAdapter.kt:

package com.example.todolist

class TodoAdapter{
	 private val todos: MutableList <Todo>
} {
	class TodoViewHolder(itemView:view) : RecyclerView.ViewHolder(itemView)

	/* 
		CTRL + i ==> ENTER ==> CTRL + a ==> ENTER
		Use layout inflater to get reference to specific view
		Remove definitions of onCreateViewHolder, onBindViewHolder, getItemCount functions
		add return ToDoViewHolder( ... ) in each definition as this is the set return-type
		Need to pass ItemView as parameter where LayoutInflater is used to get a reference to specific view from XML
		Get state of application using LayoutInflator 
	*/
	
	fun addTodo(todo: Todo){
		todos.add(Todo)
		notifyItemInserted(todos.size + 1)
	}
	
	fun deleteTodo(){
		todos.removeAll { todo -> todo.isChecked }
		notifyDataSetChanged()
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
		return TodoViewHolder(
				LayoutInflater.view(parent.context).inflate(
				R.layout.item_todo,
				attachToRoot false
			)
		)
	}	
	
	override fun onBindViewHolder(holder: TodoViewHolder, position: Int){
		val curTodo = todos[position]
		curTodo.ischecked()
		holder.itemView
		holder.itemView.apply{
			tvTodoTitle.text = currTodo.title
			cbDone.isChecked = curTodo.isChecked // Strike Through any checked text and vice-versa
		}
	}
			
	private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
		if(isChecked){
			tvTodo.title.paintflags =  tvTodoTitle.paintflags OR STRIKE_THRU_TEXT_FLAG
			
			cb.done(isChecked) = curTodo.isChecked
			toggleStrikeThrough(tvTodo.title, curTodo.isChecked)
			cbDone.setOnCheckedChangeListener { _ , isChecked =>
				toggleStrikeThrough(tvTodo.title, curTodo.isChecked)
				curTodo.ischecked = !curTodo.isChecked
			}
		}
	}
	
	override fun getItemCount() : Int {
		return todos.size // RecyclerView needs to know the number of items it has to display that are in todo list
	}
}



/*
MainActivity.kt

package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}
*/



/*
activity_main.xml:

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rvTodoItems"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toTopOf="@+id/etTodoTitle"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/etTodoTitle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Enter Todo Title"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/btnAddTodo"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/btnAddTodo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Add Todo"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/btnDeleteDoneTodos" />

    <Button
        android:id="@+id/btnDeleteDoneTodos"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Delete done"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
*/


/*
item_todo.xml:

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rvTodoItems"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toTopOf="@+id/etTodoTitle"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/etTodoTitle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Enter Todo Title"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/btnAddTodo"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/btnAddTodo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Add Todo"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/btnDeleteDoneTodos" />

    <Button
        android:id="@+id/btnDeleteDoneTodos"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Delete done"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
*/
