// Copyright (C) 2013 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.avatars.ondisk;

import com.google.gerrit.common.Nullable;
import com.google.gerrit.extensions.annotations.Listen;
import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.extensions.annotations.PluginData;
import com.google.gerrit.extensions.restapi.Url;
import com.google.gerrit.server.IdentifiedUser;
import com.google.gerrit.server.avatar.AvatarProvider;
import com.google.gerrit.server.config.CanonicalWebUrl;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.nio.file.Files;
import java.nio.file.Path;

@Listen
@Singleton
public class OnDiskAvatarProvider implements AvatarProvider {
  private String avatarUrl;
  private Path pluginDir;

  @Inject
  OnDiskAvatarProvider(@PluginName String pluginName,
      @CanonicalWebUrl @Nullable String canonicalUrl,
      @PluginData Path pluginDir) {
    this.avatarUrl = canonicalUrl + "/plugin/" + pluginName + "/data/";
    this.pluginDir = pluginDir;
    
  }

  @Override
  public String getUrl(IdentifiedUser forUser, int imageSize) {
    String fileName = forUser.getUserName() + ".png";
    Path localFile = pluginDir.resolve(fileName);
    if(Files.isReadable(localFile) && Files.isRegularFile(localFile)) {
      return avatarUrl + Url.encode(fileName);
    } else {
      return null;
    }
  }

  @Override
  public String getChangeAvatarUrl(IdentifiedUser forUser) {
    return null;
  }
}
