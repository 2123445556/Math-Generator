package com.example.demo;
// Generates the problems that will be displayed by the HelloApplication class.
public class MathGenerator {
    private int numProblems;
    public int[] answers;
    public int answersCorrect;
    public int answersIncorrect;
    private String name;
    private String fruit;
    public String[] problems;
    int cnt = 0;
    // Constructs the array of problems depending on the parameters such as number of problems type of problems
    // and general questions for a bonus question at the end.
    public MathGenerator(int numProblems, String type, String favoriteFruit, String name){
        this.numProblems = numProblems;
        fruit = favoriteFruit;
        answers = new int[numProblems+1];
        this.name = name;
        problems = new String[numProblems];
        for(int i = 0; i < numProblems; i++){
            if(type.equals("Addition")){
                problems[i] = generateAdditionProblem();
            }
            else if(type.equals("Subtraction")){
                problems[i] = generateSubtractionProblem();
            }
            else if(type.equals("Division")){
                problems[i] = generateDivisionProblem();
            }
            else if(type.equals("Multiplication")){
                problems[i] = generateMultiplicationProblem();
            }
            else if(type.equals("Add/Sub")){
                if(i % 2  == 0) {
                    problems[i] = generateAdditionProblem();
                }
                else {
                    problems[i] = generateSubtractionProblem();
                }
            }
            else if(type.equals("Multi/Div")){
                if(i % 2  == 0) {
                    problems[i] = generateDivisionProblem();
                }
                else {
                    problems[i] = generateMultiplicationProblem();
                }
            }
            else if(type.equals("All of the Problems")){
                if(i % 2 == 0){
                    problems[i] = generateAdditionProblem();
                }
                else if(i % 3 == 0){
                    problems[i] = generateMultiplicationProblem();
                }
                else if(i % 5 == 0){
                    problems[i] = generateDivisionProblem();
                }
                else{
                    problems[i] = generateSubtractionProblem();
                }
            }
        }

    }
   // Generates a random addition problem from 0 to 1000.
    public String generateAdditionProblem(){
        while(true) {
            int rand = (int) (Math.random() * 1000);
            int rand2 = (int) (Math.random() * 1000);
            if(rand > rand2) {
                answers[cnt] = rand + rand2;
                cnt++;
                return rand + " + " + rand2 + " = ";
            }
        }
    }
    // Generates a random substraction problem from 0 to 1000 but will never equal a negative number.
    public String generateSubtractionProblem(){
        while(true) {
            int rand = (int) (Math.random() * 1000);
            int rand2 = (int) (Math.random() * 1000);
            if(rand > rand2 && rand - rand2 >= 0) {
                answers[cnt] = rand - rand2;
                cnt++;
                return rand + " - " + rand2 + " = ";
            }
        }
    }
    // Generates a random division problem that is divisible and doesn't result in a decimal.
    public String generateDivisionProblem(){
        while(true){
            int rand = (int)(Math.random() * 500)+1;
            int rand2 = (int)(Math.random() * 400)+1;
            if(rand > rand2) {
                if ((rand % rand2) == 0) {
                    answers[cnt] = rand / rand2;
                    cnt++;
                    return rand + " / " + rand2 + " = ";
                }
            }
        }
    }
    // Generates a random multiplication problem that has a product less than or equal to 1000.
    public String generateMultiplicationProblem(){
        while(true){
            int rand = (int)(Math.random() * 100);
            int rand2 = (int)(Math.random() * 100);
            if(rand * rand2 >= 0 && rand * rand2 <= 1000){
                answers[cnt] = rand * rand2;
                cnt++;
                return rand + " x " + rand2 + " = ";
            }
        }
    }
    // Generates a word problem based on what name you typed and what your favorite fruit is and
    // also generates a random number for the amount of money you have.
    public String generateWordProblem(){
        int rand2 = (int)(Math.random() * 100) + 1;
        answers[cnt] = (int)((rand2 / 5.65)+.5);
        cnt++;
        return "If each " + fruit + " at a fruit stand cost's $5.65 and " + name + " has $" + rand2 + ". How many " + fruit + "s can " + name + " buy?";



    }


    public int getAnswer(int b){
        return answers[b];
    }
    public void incrementAnswersCorrect(){
        answersCorrect++;
    }
    public int getAnswersIncorrect(){
        return answersIncorrect;
    }
    public int getAnswersCorrect(){return answersCorrect;}
    public String getPercentageCorrect(){
        return (int)(((double)answersCorrect / (numProblems+1)) * 100) + "%";
    }
    public String[] getProblems(){return problems;}
    public int getNumProblems(){
        return numProblems;
    }
    public void incrementAnswersIncorrect(){answersIncorrect++;}
}
