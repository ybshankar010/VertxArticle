# Vert.x Example

## Overview

This project showcases a Vert.x example designed for a Medium article. It consists of two key components:

- **Producer Verticle**
- **Consumer Verticle**

## Dependencies

Before getting started, ensure the following dependencies are set up:

- [Kind](https://formulae.brew.sh/formula/kind) cluster
- Zookeeper setup

## Installation and Build Steps

### 1. Install Kind Cluster Locally

```bash
brew install kind
```

### 2. Create a Local Cluster

```bash
kind create cluster --config=<your-config.yml>
```

### 3. Install Zookeeper in the Cluster

```bash
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install my-zookeeper bitnami/zookeeper --version 12.7.0
```

### 4. Create a Namespace

```bash
kubectl create namespace articles
```

### 5. Build and Push Docker Image

```bash
docker build -t vertxzookeeperexample -f images/Dockerfile .
docker tag vertxzookeeperexample:latest vertxzookeeperexample:<image-tag>
kind load docker-image vertxzookeeperexample:<image-tag> --name <cluster name>
```

Note: The above steps involve creating a Docker image and pushing it to a local container registry. Adjust these steps based on your local system.

### 6. Deploy Verticles

```bash
kubectl apply -f deployments/producer_deployment.yaml
kubectl apply -f deployments/consumer_deployment.yaml
```

These deployment files specify the configurations for both the producer and consumer verticles.

## Conclusion

By following these steps, you should have a fully operational Vert.x example running in your local Kind cluster. Adjust configurations and parameters as needed for your specific environment.