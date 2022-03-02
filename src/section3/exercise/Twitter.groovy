package section3.exercise

class Twitter {
    static void main(String[] args) {
        Tweet firstTweet = new Tweet("First Tweet", "King");
        println firstTweet;

        Tweet secondTweet = new Tweet("Second Tweet", "Ioane");
        println secondTweet;

        // Bonus task
        // Groovyc: Invalid duplicate class definition of class section3.exercise.Tweet :
        // The source /Users/loveking/Desktop/My Stuff/Foster Moore/FMGroovyExercises/src/section3/exercise/Tweet.groovy contains
        // at least two definitions of the class section3.exercise.Tweet.
        // One of the classes is an explicit generated class using the class statement, the other is a class generated
        // from the script body based on the file name. Solutions are to change the file name or to change the class name.

        // Groovy creates another Tweet class then they clash and give the error
    }
}
