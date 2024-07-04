import java

def say_hello(arg, context):
    print(f"hello {arg}")
    # call android Toast.makeText() function
    Toast = java.jclass("android.widget.Toast")
    Toast.makeText(context, f"hello {arg} (this is a message from python)", Toast.LENGTH_LONG).show()
    Toast = None
    context = None