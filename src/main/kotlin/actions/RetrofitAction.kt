package actions

import com.intellij.openapi.actionSystem.AnActionEvent
import constants.*
import constants.CodeGeneratorConstants.IMPLEMENTATION_CONFIG_NAME
import core.ActionHandler
import model.DependencyModel
import model.FileModel
import model.ModificationModel
import model.ModificationStep
import utils.extensions.DOT
import utils.extensions.getPackageName

class RetrofitAction : BaseAction() {

    override fun actionPerformed(e: AnActionEvent) {
        super.actionPerformed(e)

        if (project != null && module != null) {

            val templateModel = mapOf(PACKAGE_KEY to module!!.getPackageName() + Char.DOT + RETROFIT_F_NAME)

            val dataModel = ModificationModel(
                steps = listOf(
                    ModificationStep.GradleModificationStep.DependencyModification(
                        moduleDependencies = listOf(
                            DependencyModel(
                                name = RETROFIT_D,
                                version = RETROFIT_VERSION,
                                componentName = IMPLEMENTATION_CONFIG_NAME
                            ),
                        ),
                    ),
                    ModificationStep.GenerateCodeStep(
                        files = listOf(
                            FileModel(
                                name = RETROFIT_T_API,
                                templateModel = templateModel,
                                isOpenInEditor = true
                            ),
                        ),
                        dirName = RETROFIT_F_NAME
                    ),
                    ModificationStep.NotificationStep(
                        message = "Bang bang bang Retrofit inda house!"
                    )
                ),
                module = module!!
            )

            ActionHandler.getInstance(project!!).handle(dataModel)
        }
    }
}