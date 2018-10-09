package com.microsoft.jenkins.artifactmanager;

import com.microsoftopentechnologies.windowsazurestorage.beans.StorageAccountInfo;
import com.microsoftopentechnologies.windowsazurestorage.helper.AzureCredentials;
import hudson.model.Item;
import hudson.util.DescribableList;
import jenkins.model.ArtifactManagerConfiguration;
import jenkins.model.ArtifactManagerFactory;
import jenkins.model.ArtifactManagerFactoryDescriptor;

public class Utils {
    public static AzureArtifactConfig getArtifactConfig() {
        ArtifactManagerConfiguration artifactManagerConfiguration = ArtifactManagerConfiguration.get();
        DescribableList<ArtifactManagerFactory, ArtifactManagerFactoryDescriptor> artifactManagerFactories =
                artifactManagerConfiguration.getArtifactManagerFactories();
        AzureArtifactManagerFactory azureArtifactManagerFactory = artifactManagerFactories.get(AzureArtifactManagerFactory.class);
        AzureArtifactConfig config = azureArtifactManagerFactory.getConfig();
        return config;
    }

    public static StorageAccountInfo getStorageAccount(Item item) {
        AzureArtifactConfig config = getArtifactConfig();
        AzureCredentials.StorageAccountCredential accountCredentials =
                AzureCredentials.getStorageAccountCredential(item, config.getStorageCredentialId());
        StorageAccountInfo accountInfo = AzureCredentials.convertToStorageAccountInfo(accountCredentials);
        return accountInfo;
    }

    private Utils() {
    }
}