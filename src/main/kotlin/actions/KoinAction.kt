package actions

import com.intellij.openapi.actionSystem.AnActionEvent
import constants.CodeGeneratorConstants.IMPLEMENTATION_CONFIG_NAME
import constants.KOIN_D_CORE
import constants.KOIN_D_SCOPE
import constants.KOIN_D_VIEW_MODEL
import constants.KOIN_VERSION
import core.ActionHandler
import model.DependencyModel
import model.ModificationModel
import model.ModificationStep

class KoinAction : BaseAction() {

    override fun actionPerformed(e: AnActionEvent) {
        super.actionPerformed(e)

        if (project != null && module != null) {
            val dataModel = ModificationModel(
                steps = listOf(
                    ModificationStep.GradleModificationStep.DependencyModification(
                        moduleDependencies = listOf(
                            DependencyModel(
                                name = KOIN_D_SCOPE,
                                version = KOIN_VERSION,
                                componentName = IMPLEMENTATION_CONFIG_NAME
                            ),
                            DependencyModel(
                                name = KOIN_D_CORE,
                                version = KOIN_VERSION,
                                componentName = IMPLEMENTATION_CONFIG_NAME
                            ),
                            DependencyModel(
                                name = KOIN_D_VIEW_MODEL,
                                version = KOIN_VERSION,
                                componentName = IMPLEMENTATION_CONFIG_NAME
                            )
                        )
                    ),
                    ModificationStep.ExistingFiles()
                ),
                module = module!!
            )
            ActionHandler.getInstance(project!!).handle(dataModel)
        }
    }
}