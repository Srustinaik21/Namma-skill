package com.nammaskill;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = NammaSkillApp.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface NammaSkillApp_GeneratedInjector {
  void injectNammaSkillApp(NammaSkillApp nammaSkillApp);
}
