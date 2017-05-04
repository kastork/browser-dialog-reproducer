package net.kirkstork.reproducers

import tornadofx.*
import java.io.File

class Demo : App(MainView::class)

class MainView : View("Demo") {

	val preferences: DemoPreferences by inject()
	val preferencesModel = DemoPreferencesModel(preferences)

	override val root = borderpane {
		center {
			form {
				fieldset("Toolbox Global Setup") {
					field("Downloaded Archives Directory") {
						textfield(preferencesModel.filePath)
						button("...") {
							setOnAction {
								preferencesModel.filePath.value = chooseDirectory(
										"Choose a Directory",
										File("/"))!!.absolutePath
							}
						}
					}
				}
			}
		}
	}
}

class DemoPreferencesModel(var preferences: DemoPreferences) : ViewModel() {
	val filePath = bind { preferences.filePathProperty() }
}

class DemoPreferences : ViewModel() {
	var filePath by property<String>()
	fun filePathProperty() = getProperty(DemoPreferences::filePath)

}
