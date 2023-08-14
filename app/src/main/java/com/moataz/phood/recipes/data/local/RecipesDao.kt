package com.moataz.phood.recipes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moataz.phood.recipes.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(vararg recipeEntity: RecipeEntity)

    @Query("DELETE FROM RECIPE_TABLE")
    suspend fun deleteRecipe()

    @Query("SELECT * FROM RECIPE_TABLE WHERE recipeType = :recipeType")
    fun getRecipesByCategory(recipeType: String): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM RECIPE_TABLE")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM RECIPE_TABLE")
    suspend fun getRecipes(): List<RecipeEntity>

    @Query("SELECT * FROM RECIPE_TABLE WHERE id = :id")
    fun getRecipeDetailsById(id: String): Flow<RecipeEntity>

    @Query("UPDATE RECIPE_TABLE SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun setRecipeFavoriteStatus(id: String, isFavorite: Boolean)
}
