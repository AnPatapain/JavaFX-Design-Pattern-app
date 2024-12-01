package fr.insa.bourges.firstapplicationjfx.features.recipe.filter;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;

import java.util.List;

public class FilterContext {
    private List<Recipe> recipes;
    private double time;
    private String args;

    public FilterContext(){}

    public List<Recipe> getRecipes() {return recipes;}

    public void setRecipes(List<Recipe> recipes) {this.recipes = recipes;}

    public double getTime() {return time;}

    public void setTime(double time) {this.time = time;}

    public String getArgs() {return args;}

    public void setArgs(String args) {this.args = args;}
}
