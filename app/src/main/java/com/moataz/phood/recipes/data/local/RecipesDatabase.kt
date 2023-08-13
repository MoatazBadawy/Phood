package com.moataz.phood.recipes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moataz.phood.recipes.data.local.entity.RecipeEntity
import com.moataz.phood.recipes.data.local.utils.Converters

@Database(
    entities = [RecipeEntity::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}
