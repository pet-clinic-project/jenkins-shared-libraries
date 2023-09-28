// vars/myVarFunction.groovy
def call() {
    // Access the global variable from src/GlobalVars.groovy
    echo "Global variable value: ${GlobalVar.myGlobalVar}"
}
