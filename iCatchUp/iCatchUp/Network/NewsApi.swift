//
//  NewsApi.swift
//  iCatchUp
//
//  Created by Developer User on 10/18/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import Foundation
import os
import Alamofire



class NewsApi {
    static let baseUrl = "https://newsapi.org"
    static let topHeadlinesUrl = "\(baseUrl)/v2/top-headlines"
    static let everythingUrl = "\(baseUrl)/v2/everything"
    static let sourcesUrl = "\(baseUrl)/v2/sources"
    
    static private func get<T: Decodable>(
        from urlString: String,
        parameters: [String : String],
        responseType: T.Type,
        responseHandler: @escaping ((T) -> Void),
        errorHandler: @escaping ((Error) -> Void)) {
        // Validate URL
        guard let url = URL(string: urlString) else {
            let message = "Error on URL"
            os_log("%@", message)
            return
        }
        // Make the Request
        Alamofire.request(url, parameters: parameters)
            .validate()
            .responseJSON(completionHandler: { response in
            switch response.result {
            case .success(_):
                do {
                    let decoder = JSONDecoder()
                    if let data = response.data {
                        let dataResponse = try decoder.decode(responseType, from: data)
                        responseHandler(dataResponse)
                    }
                } catch {
                    errorHandler(error)
                }
            case .failure(let error):
                errorHandler(error)
            }
        })
    }
    
    static func getSources(
        apiKey: String,
        responseHandler: @escaping ((SourcesResponse) -> Void),
        errorHandler: @escaping ((Error) -> Void)) {
        let parameters = ["apiKey" : apiKey]
        self.get(
            from: sourcesUrl,
            parameters: parameters,
            responseType: SourcesResponse.self,
            responseHandler: responseHandler,
            errorHandler: errorHandler)
    }
    
    static func getTopHeadlines(
        apiKey: String,
        responseHandler: @escaping ((ArticlesResponse) -> Void),
        errorHandler: @escaping ((Error) -> Void)) {
        let parameters = ["apiKey" : apiKey, "country" : "us"]
        self.get(
            from: topHeadlinesUrl,
            parameters: parameters,
            responseType: ArticlesResponse.self,
            responseHandler: responseHandler,
            errorHandler: errorHandler)
    }
    
    static func getEverything(
        apiKey: String,
        fromSources sources: String?,
        responseHandler: @escaping ((ArticlesResponse) -> Void),
        errorHandler: @escaping ((Error) -> Void)) {
        var parameters = ["apiKey" : apiKey]
        if let sources = sources {
            parameters["sources"] = sources
        }
        self.get(
            from: everythingUrl,
            parameters: parameters,
            responseType: ArticlesResponse.self,
            responseHandler: responseHandler,
            errorHandler: errorHandler)
    }
}
