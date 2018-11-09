//
//  ArticlesResponse.swift
//  iCatchUp
//
//  Created by Developer User on 10/18/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import Foundation

struct ArticlesResponse: Codable {
    var status: String?
    var code: String?
    var message: String?
    var totalResults: Int?
    var articles: [Article]?
}
