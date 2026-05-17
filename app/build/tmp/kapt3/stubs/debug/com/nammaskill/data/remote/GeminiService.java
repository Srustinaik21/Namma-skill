package com.nammaskill.data.remote;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0019\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\b"}, d2 = {"Lcom/nammaskill/data/remote/GeminiService;", "", "()V", "API_KEY", "", "getCareerGuidance", "query", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class GeminiService {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String API_KEY = "YOUR_GEMINI_API_KEY";
    
    @javax.inject.Inject
    public GeminiService() {
        super();
    }
    
    /**
     * In a production app, you would use the Google AI SDK for Android:
     * implementation("com.google.ai.client.generativeai:generativeai:0.2.0")
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCareerGuidance(@org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
}