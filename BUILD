load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "avatars-ondisk",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**/*"]),
    manifest_entries = [
        "Gerrit-PluginName: avatars-ondisk",
        "Implementation-Title: OnDisk Avatar plugin",
        "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/avatars-ondisk",
        "Gerrit-AvatarProvider: com.googlesource.gerrit.plugins.avatars.ondisk.OnDiskAvatarProvider",
    ],
)
