// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	alias(libs.plugins.android.application) apply false
	alias(libs.plugins.kotlin.android) apply false
//	id("org.jetbrains.kotlin.android") version "1.9.0" apply false
	id("com.google.dagger.hilt.android") version "2.48" apply false
	id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
}

buildscript {
	dependencies {
		classpath(libs.hilt.android.gradle.plugin)
		classpath(libs.secrets.gradle.plugin)
		classpath(libs.kotlin.gradle.plugin)
	}
}